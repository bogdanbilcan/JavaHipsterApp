import { IDealer } from 'app/shared/model/dealer.model';

export interface IStoc {
  id?: number;
  htrocarno?: number;
  resdealerid?: number;
  anfabricatieciv?: number;
  tipautovehicul?: string;
  codculoareexterior?: string;
  descculoareexterior?: string;
  vopseametalizata?: string;
  culoareinterior?: string;
  observatii?: string;
  locatie?: string;
  omologareind?: string;
  lunasosireintara?: string;
  rezervata?: string;
  dataexpirarerez?: string;
  dealers?: IDealer[];
}

export class Stoc implements IStoc {
  constructor(
    public id?: number,
    public htrocarno?: number,
    public resdealerid?: number,
    public anfabricatieciv?: number,
    public tipautovehicul?: string,
    public codculoareexterior?: string,
    public descculoareexterior?: string,
    public vopseametalizata?: string,
    public culoareinterior?: string,
    public observatii?: string,
    public locatie?: string,
    public omologareind?: string,
    public lunasosireintara?: string,
    public rezervata?: string,
    public dataexpirarerez?: string,
    public dealers?: IDealer[]
  ) {}
}
