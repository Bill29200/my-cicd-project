import { Component, OnInit, OnDestroy, NgZone } from '@angular/core';
import { DataService } from '../../services/data.service';
import { AuthService } from '../../services/auth.service';
import { Fastfood } from '../../models/fastfood';
import { User } from '../../models/user';
import { Role } from '../../enums/role';
import { lastValueFrom, Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged, takeUntil } from 'rxjs/operators';
import {FastfoodDTO} from "../../models/FastfoodDTO";

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss']
})
export class AdminDashboardComponent implements OnInit, OnDestroy {
  fastfoods: Fastfood[] = [];
  showAddForm: boolean = false;
  showConfirmForm: boolean = false;
  titreModal: string = 'Ajouter Fastfood';
  modalValidBtn: string = 'Ajouter';
  tmpfastfood: Fastfood = {
    fastfoodId: 0,
    intitule: '',
    adresse: '',
    telephone: '',
    logoUrl: '',
    gerant: { userId: 0, nom: '', password: '', role: Role.GERANT },
    serveur: { userId: 0, nom: '', password: '', role: Role.SERVEUR }
  };
  tmpGerant: User = { userId: 0, nom: '', password: '', role: Role.GERANT };
  tmpServeur: User = { userId: 0, nom: '', password: '', role: Role.SERVEUR };
  tmpfastfoodId: number = 0;
  user: User = { userId: 0, nom: '', password: '', role: Role.ADMIN };
  errorMessage: string = '';
  searchMessage: string = '';
  private searchSubject = new Subject<string>();
  private destroy$ = new Subject<void>();

  constructor(
    private dataService: DataService,
    private authService: AuthService,
    private ngZone: NgZone
  ) {
    this.user = this.dataService.actualUser;
  }

  ngOnInit(): void {
    console.log('User:', this.user);
   // this.user = this.authService.getCurrentUser() || this.user;
    this.loadFastfoods();
    this.setupSearch();
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  private setupSearch(): void {
    this.searchSubject.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      takeUntil(this.destroy$)
    ).subscribe(keyword => {
      this.ngZone.run(() => this.searchFastfood(keyword)); // Exécuter dans NgZone
    });
  }

  onSearchChange(keyword: string): void {
    this.searchSubject.next(keyword);
  }

  async loadFastfoods(): Promise<void> {
    try {
      const fastfoods = await lastValueFrom(this.dataService.getAllFastfoods());
      this.fastfoods = fastfoods;
      this.searchMessage = fastfoods.length ? '' : 'Aucun fastfood disponible';
      console.log('Fastfoods chargés:', fastfoods);
    } catch (err: any) {
      this.errorMessage = 'Erreur lors du chargement des fastfoods';
      console.error(err);
    }
  }
//......................................................................................searchFastfood
  public searchFastfood(keyword: string): void {
    const results: Fastfood[] = [];

    for (const fastfood of this.fastfoods) {
      if (fastfood.intitule.toLowerCase().indexOf(keyword.toLowerCase()) !== -1
        || fastfood.adresse.toLowerCase().indexOf(keyword.toLowerCase()) !== -1
        || fastfood.telephone.toLowerCase().indexOf(keyword.toLowerCase()) !== -1
        || fastfood.gerant.nom.indexOf(keyword.toLowerCase()) !== -1
        || fastfood.serveur.nom.toLowerCase().indexOf(keyword.toLowerCase()) !== -1 ) {
        results.push(fastfood);
      }
    }
    this.fastfoods = results;
    if (results.length === 0 || !keyword) {
      this.loadFastfoods();
    }
  }
//......................................................................................searchFastfood
  createFastfood(): void {
    this.titreModal = 'Ajouter Fastfood';
    this.modalValidBtn = 'Ajouter';
    this.resetForm();
    this.showAddForm = true;
  }

  updateFastfood(id: number, fastfood: Fastfood): void {
    this.titreModal = 'Modifier Fastfood';
    this.modalValidBtn = 'Modifier';
    this.tmpfastfoodId = id;
    this.tmpfastfood = {
      ...fastfood,
      gerant: fastfood.gerant || { userId: 0, nom: '', password: '', role: Role.GERANT },
      serveur: fastfood.serveur || { userId: 0, nom: '', password: '', role: Role.SERVEUR }
    };
    this.tmpGerant = { ...this.tmpfastfood.gerant };
    this.tmpServeur = { ...this.tmpfastfood.serveur };
    this.showAddForm = true;
  }

  async submitFastfood(): Promise<void> {
    this.errorMessage = '';
    if (!this.tmpfastfood.intitule || !this.tmpfastfood.adresse || !this.tmpfastfood.telephone ||
      !this.tmpGerant.nom || !this.tmpGerant.password || !this.tmpServeur.nom || !this.tmpServeur.password) {
      this.errorMessage = 'Veuillez remplir tous les champs obligatoires';
      return;
    }

    try {
      if (this.modalValidBtn === 'Ajouter') {
        const dto: FastfoodDTO = {
          intitule: this.tmpfastfood.intitule,
          adresse: this.tmpfastfood.adresse,
          telephone: this.tmpfastfood.telephone,
          logoUrl: this.tmpfastfood.logoUrl || '',
          nomGerant: this.tmpGerant.nom,
          passwordGerant: this.tmpGerant.password,
          nomServeur: this.tmpServeur.nom,
          passwordServeur: this.tmpServeur.password
        };
        const fastfood = await lastValueFrom(this.dataService.createFastfood(dto));
        console.log('Fastfood créé:', fastfood);
        if (fastfood) {
          await this.loadFastfoods();
          this.resetForm();
          this.showAddForm = false;
        } else {
          this.errorMessage = 'Aucun fastfood retourné par le serveur';
        }
      } else if (this.modalValidBtn === 'Modifier') {
        const gerantRequest = this.tmpGerant.userId
          ? this.dataService.updateUser(this.tmpGerant.userId, {
            ...this.tmpGerant,
            fastfoodId: this.tmpfastfoodId
          })
          : this.dataService.createUser({
            ...this.tmpGerant,
            fastfoodId: this.tmpfastfoodId
          });
        const serveurRequest = this.tmpServeur.userId
          ? this.dataService.updateUser(this.tmpServeur.userId, {
            ...this.tmpServeur,
            fastfoodId: this.tmpfastfoodId
          })
          : this.dataService.createUser({
            ...this.tmpServeur,
            fastfoodId: this.tmpfastfoodId
          });
        const [gerant, serveur] = await Promise.all([
          lastValueFrom(gerantRequest),
          lastValueFrom(serveurRequest)
        ]);
        this.tmpfastfood.gerant = gerant;
        this.tmpfastfood.serveur = serveur;
        const fastfood = await lastValueFrom(this.dataService.updateFastfood(this.tmpfastfoodId, this.tmpfastfood));
        const index = this.fastfoods.findIndex(f => f.fastfoodId === fastfood.fastfoodId);
        if (index !== -1) {
          this.fastfoods[index] = fastfood;
        }
        this.resetForm();
        this.showAddForm = false;
      }
    } catch (err: any) {
      this.errorMessage = err.message || 'Une erreur est survenue lors de la soumission';
      console.error('Erreur:', err);
    }
  }

  confirmeModalShow(id: number): void {
    this.tmpfastfoodId = id;
    const fastfood = this.fastfoods.find(f => f.fastfoodId === id) || this.tmpfastfood;
    this.tmpfastfood = { ...fastfood };
    this.tmpGerant = fastfood.gerant || { userId: 0, nom: '', password: '', role: Role.GERANT };
    this.tmpServeur = fastfood.serveur || { userId: 0, nom: '', password: '', role: Role.SERVEUR };
    this.showConfirmForm = true;
  }

  delete(id: number): void {
    this.dataService.deleteFastfood(id).subscribe({
      next: () => {
        this.fastfoods = this.fastfoods.filter(f => f.fastfoodId !== id);
        this.cancelDelete();
      },
      error: (err) => {
        this.errorMessage = 'Erreur lors de la suppression du fastfood';
        console.error(err);
      }
    });
  }

  cancel(): void {
    this.showAddForm = false;
    this.resetForm();
  }

  cancelDelete(): void {
    this.showConfirmForm = false;
    this.resetForm();
  }

  private resetForm(): void {
    this.tmpfastfood = {
      fastfoodId: 0,
      intitule: '',
      adresse: '',
      telephone: '',
      logoUrl: '',
      gerant: { userId: 0, nom: '', password: '', role: Role.GERANT },
      serveur: { userId: 0, nom: '', password: '', role: Role.SERVEUR }
    };
    this.tmpGerant = { userId: 0, nom: '', password: '', role: Role.GERANT };
    this.tmpServeur = { userId: 0, nom: '', password: '', role: Role.SERVEUR };
    this.tmpfastfoodId = 0;
    this.errorMessage = '';
    this.searchMessage = '';
  }
}
