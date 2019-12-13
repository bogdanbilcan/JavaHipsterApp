import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { INotifTemplate } from 'app/shared/model/notif-template.model';

type EntityResponseType = HttpResponse<INotifTemplate>;
type EntityArrayResponseType = HttpResponse<INotifTemplate[]>;

@Injectable({ providedIn: 'root' })
export class NotifTemplateService {
  public resourceUrl = SERVER_API_URL + 'api/notif-templates';

  constructor(protected http: HttpClient) {}

  create(notifTemplate: INotifTemplate): Observable<EntityResponseType> {
    return this.http.post<INotifTemplate>(this.resourceUrl, notifTemplate, { observe: 'response' });
  }

  update(notifTemplate: INotifTemplate): Observable<EntityResponseType> {
    return this.http.put<INotifTemplate>(this.resourceUrl, notifTemplate, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<INotifTemplate>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<INotifTemplate[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
