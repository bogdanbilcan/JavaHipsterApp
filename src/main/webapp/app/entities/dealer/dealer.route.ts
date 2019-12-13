import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Dealer } from 'app/shared/model/dealer.model';
import { DealerService } from './dealer.service';
import { DealerComponent } from './dealer.component';
import { DealerDetailComponent } from './dealer-detail.component';
import { DealerUpdateComponent } from './dealer-update.component';
import { IDealer } from 'app/shared/model/dealer.model';

@Injectable({ providedIn: 'root' })
export class DealerResolve implements Resolve<IDealer> {
  constructor(private service: DealerService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDealer> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((dealer: HttpResponse<Dealer>) => dealer.body));
    }
    return of(new Dealer());
  }
}

export const dealerRoute: Routes = [
  {
    path: '',
    component: DealerComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'Dealers'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DealerDetailComponent,
    resolve: {
      dealer: DealerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Dealers'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DealerUpdateComponent,
    resolve: {
      dealer: DealerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Dealers'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DealerUpdateComponent,
    resolve: {
      dealer: DealerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Dealers'
    },
    canActivate: [UserRouteAccessService]
  }
];
