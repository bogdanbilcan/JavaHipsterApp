import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';

import { IStoc } from 'app/shared/model/stoc.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { StocService } from './stoc.service';

@Component({
  selector: 'jhi-stoc',
  templateUrl: './stoc.component.html'
})
export class StocComponent implements OnInit, OnDestroy {
  stocs: IStoc[];
  eventSubscriber: Subscription;
  itemsPerPage: number;
  links: any;
  page: any;
  predicate: any;
  reverse: any;
  totalItems: number;

  constructor(protected stocService: StocService, protected eventManager: JhiEventManager, protected parseLinks: JhiParseLinks) {
    this.stocs = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.reverse = true;
  }

  loadAll() {
    this.stocService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IStoc[]>) => this.paginateStocs(res.body, res.headers));
  }

  reset() {
    this.page = 0;
    this.stocs = [];
    this.loadAll();
  }

  loadPage(page) {
    this.page = page;
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInStocs();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IStoc) {
    return item.id;
  }

  registerChangeInStocs() {
    this.eventSubscriber = this.eventManager.subscribe('stocListModification', () => this.reset());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateStocs(data: IStoc[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    for (let i = 0; i < data.length; i++) {
      this.stocs.push(data[i]);
    }
  }
}
