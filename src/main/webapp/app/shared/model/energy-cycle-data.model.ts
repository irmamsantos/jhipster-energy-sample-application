export interface IEnergyCycleData {
    id?: number;
    description?: string;
}

export class EnergyCycleData implements IEnergyCycleData {
    constructor(public id?: number, public description?: string) {}
}
