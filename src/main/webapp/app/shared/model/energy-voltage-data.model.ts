export interface IEnergyVoltageData {
    id?: number;
    description?: string;
}

export class EnergyVoltageData implements IEnergyVoltageData {
    constructor(public id?: number, public description?: string) {}
}
