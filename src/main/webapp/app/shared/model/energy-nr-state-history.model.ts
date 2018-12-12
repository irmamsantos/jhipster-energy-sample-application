import { Moment } from 'moment';

export interface IEnergyNRStateHistory {
    id?: number;
    energyType?: string;
    justification?: string;
    updateDate?: Moment;
    stateValue?: string;
    needNGRequestId?: number;
    userId?: number;
}

export class EnergyNRStateHistory implements IEnergyNRStateHistory {
    constructor(
        public id?: number,
        public energyType?: string,
        public justification?: string,
        public updateDate?: Moment,
        public stateValue?: string,
        public needNGRequestId?: number,
        public userId?: number
    ) {}
}
