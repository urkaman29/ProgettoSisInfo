import { Component } from '@angular/core';
import { Employee } from 'src/app/models/user';


@Component({
  selector: 'app-employees',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class EmployeesComponent {
searchForm: any;
searchEmployees: any;
[x: string]: any;
  employees: Employee[] = [
    // Esempio di elenco di dipendenti
    // ... altri dipendenti
  ];

  searchText: string = '';
  filterMechanic: boolean = false;
  filterStorekeeper: boolean = false;

  get filteredEmployees(): Employee[] {
    // Filtra i dipendenti in base al testo di ricerca e ai filtri
    return this.employees.filter(employee =>
      employee.name.toLowerCase().includes(this.searchText.toLowerCase()) &&
      (this.filterMechanic ? employee.role === 'meccanico' : true) &&
      (this.filterStorekeeper ? employee.role === 'magazziniere' : true)
    );
  }
}
