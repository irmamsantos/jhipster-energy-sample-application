export interface IEnergyTariffData {
    id?: number;
    description?: string;
}

export class EnergyTariffData implements IEnergyTariffData {
    constructor(public id?: number, public description?: string) {}
}
