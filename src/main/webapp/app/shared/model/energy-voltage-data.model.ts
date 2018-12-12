import { IEnergyElectricityData } from 'app/shared/model//energy-electricity-data.model';

export interface IEnergyVoltageData {
    id?: number;
    description?: string;
    energyElectricityData?: IEnergyElectricityData[];
}

export class EnergyVoltageData implements IEnergyVoltageData {
    constructor(public id?: number, public description?: string, public energyElectricityData?: IEnergyElectricityData[]) {}
}
