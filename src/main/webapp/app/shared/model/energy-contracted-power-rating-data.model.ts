export interface IEnergyContractedPowerRatingData {
    id?: number;
    description?: string;
}

export class EnergyContractedPowerRatingData implements IEnergyContractedPowerRatingData {
    constructor(public id?: number, public description?: string) {}
}
