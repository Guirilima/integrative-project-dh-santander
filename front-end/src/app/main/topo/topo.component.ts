
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { tap, map, filter, distinctUntilChanged, debounceTime, switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-topo',
  templateUrl: './topo.component.html',
  styleUrls: ['./topo.component.css']
})
export class TopoComponent implements OnInit {

  queryField = new FormControl();
  readonly SEARCH_URL = 'https://api.cdnjs.com/libraries';
  resultados$: Observable<any>;
  total: number;

  constructor(private http: HttpClient) { }

  ngOnInit() {
  }

  onSearch() {
    console.log(this.queryField.value)

    // let value = this.queryField.value;
    // if (value && (value = value.trim() !== '')) {
    //   this.resultados$ = this.http
    //     .get(this.SEARCH_URL + '?search=angular')
    //     .pipe(
    //       tap((res: any) => this.total = res.total),
    //       map((res: any) => res.results)
    //       );
    // }
  }

}
