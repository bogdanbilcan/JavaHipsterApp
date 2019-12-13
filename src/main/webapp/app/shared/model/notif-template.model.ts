export interface INotifTemplate {
  id?: number;
  emailAddresses?: string;
  message?: string;
}

export class NotifTemplate implements INotifTemplate {
  constructor(public id?: number, public emailAddresses?: string, public message?: string) {}
}
