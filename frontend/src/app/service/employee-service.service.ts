import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class EmployeeServiceService {
  [x: string]: any;
  constructor(private root: HttpClient) {}

  addEmployee(data: any): Observable<any> {
    return this.root.post('http://localhost:9090/add', data);
  }

  getEmployees(): Observable<any> {
    return this.root.get('http://localhost:9090/');
  }

  deleteEmployee(id:number): Observable<any> {
    return this.root.delete(`http://localhost:9090/del/${id}`);
  }

  updateEmployee(id:number, data:any): Observable<any> {
    return this.root.put(`http://localhost:9090/edit/${id}`, data);
  }
}
