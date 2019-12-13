import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JavaHipsterAppTestModule } from '../../../test.module';
import { NotifTemplateComponent } from 'app/entities/notif-template/notif-template.component';
import { NotifTemplateService } from 'app/entities/notif-template/notif-template.service';
import { NotifTemplate } from 'app/shared/model/notif-template.model';

describe('Component Tests', () => {
  describe('NotifTemplate Management Component', () => {
    let comp: NotifTemplateComponent;
    let fixture: ComponentFixture<NotifTemplateComponent>;
    let service: NotifTemplateService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JavaHipsterAppTestModule],
        declarations: [NotifTemplateComponent],
        providers: []
      })
        .overrideTemplate(NotifTemplateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NotifTemplateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NotifTemplateService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new NotifTemplate(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.notifTemplates[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
