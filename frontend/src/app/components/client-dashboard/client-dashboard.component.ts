// src/app/components/client-dashboard/client-dashboard.component.ts
import { Component, OnInit } from '@angular/core';
import { DataService } from '../../services/data.service';
import { Fastfood } from '../../models/fastfood';

@Component({
  selector: 'app-client-dashboard',
  templateUrl: './client-dashboard.component.html',
  styleUrls: ['./client-dashboard.component.scss']
})
export class ClientDashboardComponent implements OnInit {
  fastfoods: Fastfood[] = [];
  searchKeyword: string = '';

  constructor(private dataService: DataService) {}

  ngOnInit(): void {
    this.loadFastfoods();
  }

  loadFastfoods(): void {
    this.dataService.getAllFastfoods().subscribe(fastfoods => this.fastfoods = fastfoods);
  }

  searchFastfoods(): void {
    if (this.searchKeyword) {
      this.dataService.searchFastfoods(this.searchKeyword).subscribe(fastfoods => this.fastfoods = fastfoods);
    } else {
      this.loadFastfoods();
    }
  }
}
