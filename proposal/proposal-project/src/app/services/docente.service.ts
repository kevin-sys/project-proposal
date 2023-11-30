import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import { Docente } from '../models/docente.model';

@Injectable({
  providedIn: 'root'
})
export class DocenteService {
  url = '';
  constructor(private http: HttpClient) { }

	get(): Observable<any[]> {
		return this.http.get<any[]>(this.url);
	}

	getId(id:string): Observable<any> {
		return this.http.get<any>(this.url + id);
	}

	post(entity: Docente): Observable<any> {
		return this.http.post<any>(this.url, entity);
	}

	put(entity: Docente): Observable<any> {
		return this.http.put<any>(this.url, entity);
	}

	delete(id: string): Observable<string> {
		return this.http.delete<string>(this.url + id);
	}
}
