export interface IEnergyFuelData {
    id?: number;
    type?: string;
    product?: string;
}

export class EnergyFuelData implements IEnergyFuelData {
    constructor(public id?: number, public type?: string, public product?: string) {}
}
