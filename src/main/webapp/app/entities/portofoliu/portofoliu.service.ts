import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPortofoliu } from 'app/shared/model/portofoliu.model';

type EntityResponseType = HttpResponse<IPortofoliu>;
type EntityArrayResponseType = HttpResponse<IPortofoliu[]>;

@Injectable({ providedIn: 'root' })
export class PortofoliuService {
  public resourceUrl = SERVER_API_URL + 'api/portofolius';

  constructor(protected http: HttpClient) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPortofoliu>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPortofoliu[]>(this.resourceUrl, { params: options, observe: 'response' });
  }
}
