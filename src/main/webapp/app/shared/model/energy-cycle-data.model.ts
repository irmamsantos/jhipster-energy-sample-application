import { IEnergyElectricityData } from 'app/shared/model//energy-electricity-data.model';

export interface IEnergyCycleData {
    id?: number;
    description?: string;
    energyElectricityData?: IEnergyElectricityData[];
}

export class EnergyCycleData implements IEnergyCycleData {
    constructor(public id?: number, public description?: string, public energyElectricityData?: IEnergyElectricityData[]) {}
}
