import { Calendar } from './calendar';

export class Employee {
    id!: number;
    name!: string;
    telephone!: number;
    email!: string;
    baseSalary!: number;
    workedHours!: number;
    vacationHours!: number;
    permissionHours!: number;
    calendar!: Calendar;
}
