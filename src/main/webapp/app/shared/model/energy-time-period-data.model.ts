export interface IEnergyTimePeriodData {
    id?: number;
    description?: string;
}

export class EnergyTimePeriodData implements IEnergyTimePeriodData {
    constructor(public id?: number, public description?: string) {}
}
