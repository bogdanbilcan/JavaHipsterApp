import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INotifTemplate } from 'app/shared/model/notif-template.model';

@Component({
  selector: 'jhi-notif-template-detail',
  templateUrl: './notif-template-detail.component.html'
})
export class NotifTemplateDetailComponent implements OnInit {
  notifTemplate: INotifTemplate;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ notifTemplate }) => {
      this.notifTemplate = notifTemplate;
    });
  }

  previousState() {
    window.history.back();
  }
}
