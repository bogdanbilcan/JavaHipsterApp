import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPortofoliu } from 'app/shared/model/portofoliu.model';

@Component({
  selector: 'jhi-portofoliu-detail',
  templateUrl: './portofoliu-detail.component.html'
})
export class PortofoliuDetailComponent implements OnInit {
  portofoliu: IPortofoliu;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ portofoliu }) => {
      this.portofoliu = portofoliu;
    });
  }

  previousState() {
    window.history.back();
  }
}
