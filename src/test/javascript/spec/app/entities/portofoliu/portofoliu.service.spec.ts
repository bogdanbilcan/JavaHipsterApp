import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { PortofoliuService } from 'app/entities/portofoliu/portofoliu.service';
import { IPortofoliu, Portofoliu } from 'app/shared/model/portofoliu.model';

describe('Service Tests', () => {
  describe('Portofoliu Service', () => {
    let injector: TestBed;
    let service: PortofoliuService;
    let httpMock: HttpTestingController;
    let elemDefault: IPortofoliu;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(PortofoliuService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Portofoliu(
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
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
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        'AAAAAAA',
        0,
        false,
        false
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

      it('should return a list of Portofoliu', () => {
        const returnedFromService = Object.assign(
          {
            htrocarno: 1,
            dealer: 'BBBBBB',
            datarezsaufactura: 'BBBBBB',
            dataexpirare: 'BBBBBB',
            resdealerid: 1,
            tiplinie: 'BBBBBB',
            locatie: 'BBBBBB',
            lunaproductie: 'BBBBBB',
            lunasosireintara: 'BBBBBB',
            codmodel: 'BBBBBB',
            tipautovehicul: 'BBBBBB',
            codculoareext: 'BBBBBB',
            culoareexterior: 'BBBBBB',
            culoareIntegererior: 'BBBBBB',
            observatii: 'BBBBBB',
            numeclient: 'BBBBBB',
            numevanzator: 'BBBBBB',
            vin: 'BBBBBB',
            engineno: 'BBBBBB',
            anfabricatiecfciv: 1,
            omologareindividuala: 'BBBBBB',
            pretlista: 1,
            discountstandard: 1,
            discountsuplimentar: 1,
            trusalegislativa: 1,
            pretfinal: 1,
            avansplatit: 1,
            restdeplata: 1,
            customertrxid: 1,
            rezcustid: 'BBBBBB',
            soldcustid: 1,
            proforma: true,
            transport: true
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
