import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JavaHipsterAppTestModule } from '../../../test.module';
import { NotifTemplateDetailComponent } from 'app/entities/notif-template/notif-template-detail.component';
import { NotifTemplate } from 'app/shared/model/notif-template.model';

describe('Component Tests', () => {
  describe('NotifTemplate Management Detail Component', () => {
    let comp: NotifTemplateDetailComponent;
    let fixture: ComponentFixture<NotifTemplateDetailComponent>;
    const route = ({ data: of({ notifTemplate: new NotifTemplate(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JavaHipsterAppTestModule],
        declarations: [NotifTemplateDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(NotifTemplateDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NotifTemplateDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.notifTemplate).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
