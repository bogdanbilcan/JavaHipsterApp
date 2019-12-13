import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';

import { IPortofoliu } from 'app/shared/model/portofoliu.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { PortofoliuService } from './portofoliu.service';

@Component({
  selector: 'jhi-portofoliu',
  templateUrl: './portofoliu.component.html'
})
export class PortofoliuComponent implements OnInit, OnDestroy {
  portofolius: IPortofoliu[];
  eventSubscriber: Subscription;
  itemsPerPage: number;
  links: any;
  page: any;
  predicate: any;
  reverse: any;
  totalItems: number;

  constructor(
    protected portofoliuService: PortofoliuService,
    protected eventManager: JhiEventManager,
    protected parseLinks: JhiParseLinks
  ) {
    this.portofolius = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.reverse = true;
  }

  loadAll() {
    this.portofoliuService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IPortofoliu[]>) => this.paginatePortofolius(res.body, res.headers));
  }

  reset() {
    this.page = 0;
    this.portofolius = [];
    this.loadAll();
  }

  loadPage(page) {
    this.page = page;
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInPortofolius();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IPortofoliu) {
    return item.id;
  }

  registerChangeInPortofolius() {
    this.eventSubscriber = this.eventManager.subscribe('portofoliuListModification', () => this.reset());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginatePortofolius(data: IPortofoliu[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    for (let i = 0; i < data.length; i++) {
      this.portofolius.push(data[i]);
    }
  }
}
