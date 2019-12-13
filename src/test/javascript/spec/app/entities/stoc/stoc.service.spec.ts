import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { StocService } from 'app/entities/stoc/stoc.service';
import { IStoc, Stoc } from 'app/shared/model/stoc.model';

describe('Service Tests', () => {
  describe('Stoc Service', () => {
    let injector: TestBed;
    let service: StocService;
    let httpMock: HttpTestingController;
    let elemDefault: IStoc;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(StocService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Stoc(
        0,
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should return a list of Stoc', () => {
        const returnedFromService = Object.assign(
          {
            htrocarno: 1,
            resdealerid: 1,
            anfabricatieciv: 1,
            tipautovehicul: 'BBBBBB',
            codculoareexterior: 'BBBBBB',
            descculoareexterior: 'BBBBBB',
            vopseametalizata: 'BBBBBB',
            culoareinterior: 'BBBBBB',
            observatii: 'BBBBBB',
            locatie: 'BBBBBB',
            omologareind: 'BBBBBB',
            lunasosireintara: 'BBBBBB',
            rezervata: 'BBBBBB',
            dataexpirarerez: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
