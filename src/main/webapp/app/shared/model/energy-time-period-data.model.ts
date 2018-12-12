import { IEnergyElectricityData } from 'app/shared/model//energy-electricity-data.model';

export interface IEnergyTimePeriodData {
    id?: number;
    description?: string;
    energyElectricityData?: IEnergyElectricityData[];
}

export class EnergyTimePeriodData implements IEnergyTimePeriodData {
    constructor(public id?: number, public description?: string, public energyElectricityData?: IEnergyElectricityData[]) {}
}
