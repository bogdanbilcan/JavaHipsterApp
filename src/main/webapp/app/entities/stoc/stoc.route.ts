import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Stoc } from 'app/shared/model/stoc.model';
import { StocService } from './stoc.service';
import { StocComponent } from './stoc.component';
import { StocDetailComponent } from './stoc-detail.component';
import { IStoc } from 'app/shared/model/stoc.model';

@Injectable({ providedIn: 'root' })
export class StocResolve implements Resolve<IStoc> {
  constructor(private service: StocService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IStoc> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((stoc: HttpResponse<Stoc>) => stoc.body));
    }
    return of(new Stoc());
  }
}

export const stocRoute: Routes = [
  {
    path: '',
    component: StocComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Stocs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: StocDetailComponent,
    resolve: {
      stoc: StocResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Stocs'
    },
    canActivate: [UserRouteAccessService]
  }
];
