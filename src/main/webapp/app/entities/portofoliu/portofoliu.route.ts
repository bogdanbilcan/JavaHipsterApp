import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Portofoliu } from 'app/shared/model/portofoliu.model';
import { PortofoliuService } from './portofoliu.service';
import { PortofoliuComponent } from './portofoliu.component';
import { PortofoliuDetailComponent } from './portofoliu-detail.component';
import { IPortofoliu } from 'app/shared/model/portofoliu.model';

@Injectable({ providedIn: 'root' })
export class PortofoliuResolve implements Resolve<IPortofoliu> {
  constructor(private service: PortofoliuService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPortofoliu> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((portofoliu: HttpResponse<Portofoliu>) => portofoliu.body));
    }
    return of(new Portofoliu());
  }
}

export const portofoliuRoute: Routes = [
  {
    path: '',
    component: PortofoliuComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Portofolius'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PortofoliuDetailComponent,
    resolve: {
      portofoliu: PortofoliuResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Portofolius'
    },
    canActivate: [UserRouteAccessService]
  }
];
