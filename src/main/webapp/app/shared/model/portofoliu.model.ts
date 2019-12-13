import { IDealer } from 'app/shared/model/dealer.model';

export interface IPortofoliu {
  id?: number;
  htrocarno?: number;
  dealer?: string;
  datarezsaufactura?: string;
  dataexpirare?: string;
  resdealerid?: number;
  tiplinie?: string;
  locatie?: string;
  lunaproductie?: string;
  lunasosireintara?: string;
  codmodel?: string;
  tipautovehicul?: string;
  codculoareext?: string;
  culoareexterior?: string;
  culoareIntegererior?: string;
  observatii?: string;
  numeclient?: string;
  numevanzator?: string;
  vin?: string;
  engineno?: string;
  anfabricatiecfciv?: number;
  omologareindividuala?: string;
  pretlista?: number;
  discountstandard?: number;
  discountsuplimentar?: number;
  trusalegislativa?: number;
  pretfinal?: number;
  avansplatit?: number;
  restdeplata?: number;
  customertrxid?: number;
  rezcustid?: string;
  soldcustid?: number;
  proforma?: boolean;
  transport?: boolean;
  dealers?: IDealer[];
}

export class Portofoliu implements IPortofoliu {
  constructor(
    public id?: number,
    public htrocarno?: number,
    public dealer?: string,
    public datarezsaufactura?: string,
    public dataexpirare?: string,
    public resdealerid?: number,
    public tiplinie?: string,
    public locatie?: string,
    public lunaproductie?: string,
    public lunasosireintara?: string,
    public codmodel?: string,
    public tipautovehicul?: string,
    public codculoareext?: string,
    public culoareexterior?: string,
    public culoareIntegererior?: string,
    public observatii?: string,
    public numeclient?: string,
    public numevanzator?: string,
    public vin?: string,
    public engineno?: string,
    public anfabricatiecfciv?: number,
    public omologareindividuala?: string,
    public pretlista?: number,
    public discountstandard?: number,
    public discountsuplimentar?: number,
    public trusalegislativa?: number,
    public pretfinal?: number,
    public avansplatit?: number,
    public restdeplata?: number,
    public customertrxid?: number,
    public rezcustid?: string,
    public soldcustid?: number,
    public proforma?: boolean,
    public transport?: boolean,
    public dealers?: IDealer[]
  ) {
    this.proforma = this.proforma || false;
    this.transport = this.transport || false;
  }
}
