import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IStoc } from 'app/shared/model/stoc.model';

type EntityResponseType = HttpResponse<IStoc>;
type EntityArrayResponseType = HttpResponse<IStoc[]>;

@Injectable({ providedIn: 'root' })
export class StocService {
  public resourceUrl = SERVER_API_URL + 'api/stocs';

  constructor(protected http: HttpClient) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IStoc>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IStoc[]>(this.resourceUrl, { params: options, observe: 'response' });
  }
}
