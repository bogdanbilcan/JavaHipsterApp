import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStoc } from 'app/shared/model/stoc.model';

@Component({
  selector: 'jhi-stoc-detail',
  templateUrl: './stoc-detail.component.html'
})
export class StocDetailComponent implements OnInit {
  stoc: IStoc;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ stoc }) => {
      this.stoc = stoc;
    });
  }

  previousState() {
    window.history.back();
  }
}
