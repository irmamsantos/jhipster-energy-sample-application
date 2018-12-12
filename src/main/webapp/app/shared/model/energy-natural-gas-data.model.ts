export interface IEnergyNaturalGasData {
    id?: number;
    pressure?: string;
    echelon?: string;
    pressureDescription?: string;
}

export class EnergyNaturalGasData implements IEnergyNaturalGasData {
    constructor(public id?: number, public pressure?: string, public echelon?: string, public pressureDescription?: string) {}
}
