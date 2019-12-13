import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { INotifTemplate } from 'app/shared/model/notif-template.model';
import { NotifTemplateService } from './notif-template.service';
import { NotifTemplateDeleteDialogComponent } from './notif-template-delete-dialog.component';

@Component({
  selector: 'jhi-notif-template',
  templateUrl: './notif-template.component.html'
})
export class NotifTemplateComponent implements OnInit, OnDestroy {
  notifTemplates: INotifTemplate[];
  eventSubscriber: Subscription;

  constructor(
    protected notifTemplateService: NotifTemplateService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll() {
    this.notifTemplateService.query().subscribe((res: HttpResponse<INotifTemplate[]>) => {
      this.notifTemplates = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInNotifTemplates();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: INotifTemplate) {
    return item.id;
  }

  registerChangeInNotifTemplates() {
    this.eventSubscriber = this.eventManager.subscribe('notifTemplateListModification', () => this.loadAll());
  }

  delete(notifTemplate: INotifTemplate) {
    const modalRef = this.modalService.open(NotifTemplateDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.notifTemplate = notifTemplate;
  }
}
