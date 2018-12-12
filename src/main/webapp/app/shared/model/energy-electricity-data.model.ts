export interface IEnergyElectricityData {
    id?: number;
    voltageId?: number;
    tariffId?: number;
    cycleId?: number;
    timePeriodId?: number;
}

export class EnergyElectricityData implements IEnergyElectricityData {
    constructor(
        public id?: number,
        public voltageId?: number,
        public tariffId?: number,
        public cycleId?: number,
        public timePeriodId?: number
    ) {}
}
