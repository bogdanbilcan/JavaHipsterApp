import { IUser } from 'app/core/user/user.model';
import { TipuriAuto } from 'app/shared/model/enumerations/tipuri-auto.model';

export interface IDealer {
  id?: number;
  name?: string;
  description?: string;
  tipAutovehicule?: TipuriAuto;
  dealerId?: string;
  users?: IUser[];
  stocResdealerid?: string;
  stocId?: number;
  portofoliuResdealerid?: string;
  portofoliuId?: number;
}

export class Dealer implements IDealer {
  constructor(
    public id?: number,
    public name?: string,
    public description?: string,
    public tipAutovehicule?: TipuriAuto,
    public dealerId?: string,
    public users?: IUser[],
    public stocResdealerid?: string,
    public stocId?: number,
    public portofoliuResdealerid?: string,
    public portofoliuId?: number
  ) {}
}
