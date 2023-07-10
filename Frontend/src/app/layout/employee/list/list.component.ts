import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Employee } from 'src/app/models/user';
import { ListService } from 'src/app/service/list/list.service';

@Component({
  selector: 'app-employees',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
  employees: Employee[] = [];
  searchForm: FormGroup;
  filteredEmployees: Employee[] | undefined;

  constructor(
    private listService: ListService,
    private formBuilder: FormBuilder
  ) {
    this.searchForm = this.formBuilder.group({
      searchText: [''],
      filterMechanic: [false],
      filterStorekeeper: [false]
    });
  }

  ngOnInit(): void {
    this.loadEmployees();
  }

  loadEmployees(): void {
    this.listService.getEmployees().subscribe(
      (employees: any) => {
        this.employees = employees as Employee[];  // Utilizzo di 'as' per forzare il tipo a Employee[]
        this.filterEmployees();
      },
      error => {
        console.log('Errore nella ricerca del dipendente:', error);
      }
    );
  }
  

  filterEmployees(): void {
    const searchText = this.searchForm.get('searchText')?.value;
    const filterMechanic = this.searchForm.get('filterMechanic')?.value;
    const filterStorekeeper = this.searchForm.get('filterStorekeeper')?.value;

    this.filteredEmployees = this.employees.filter(employee => {
      // Filtrare per nome
      if (searchText && !employee.name.toLowerCase().includes(searchText.toLowerCase())) {
        return false;
      }

      // Filtrare per ruolo
      if (
        (filterMechanic && employee.role !== 'meccanico') ||
        (filterStorekeeper && employee.role !== 'magazziniere')
      ) {
        return false;
      }

      return true;
    });
  }

  searchEmployees(): void {
    this.filterEmployees();
  }
}
