import { Moment } from 'moment';

export interface IEnergyNeedNGRequest {
    id?: number;
    energyType?: string;
    updateDate?: Moment;
    userId?: number;
}

export class EnergyNeedNGRequest implements IEnergyNeedNGRequest {
    constructor(public id?: number, public energyType?: string, public updateDate?: Moment, public userId?: number) {}
}
