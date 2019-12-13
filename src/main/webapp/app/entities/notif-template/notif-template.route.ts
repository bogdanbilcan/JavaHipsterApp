import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { NotifTemplate } from 'app/shared/model/notif-template.model';
import { NotifTemplateService } from './notif-template.service';
import { NotifTemplateComponent } from './notif-template.component';
import { NotifTemplateDetailComponent } from './notif-template-detail.component';
import { NotifTemplateUpdateComponent } from './notif-template-update.component';
import { INotifTemplate } from 'app/shared/model/notif-template.model';

@Injectable({ providedIn: 'root' })
export class NotifTemplateResolve implements Resolve<INotifTemplate> {
  constructor(private service: NotifTemplateService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INotifTemplate> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((notifTemplate: HttpResponse<NotifTemplate>) => notifTemplate.body));
    }
    return of(new NotifTemplate());
  }
}

export const notifTemplateRoute: Routes = [
  {
    path: '',
    component: NotifTemplateComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'NotifTemplates'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: NotifTemplateDetailComponent,
    resolve: {
      notifTemplate: NotifTemplateResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'NotifTemplates'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: NotifTemplateUpdateComponent,
    resolve: {
      notifTemplate: NotifTemplateResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'NotifTemplates'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: NotifTemplateUpdateComponent,
    resolve: {
      notifTemplate: NotifTemplateResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'NotifTemplates'
    },
    canActivate: [UserRouteAccessService]
  }
];
