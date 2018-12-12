import { IEnergyElectricityData } from 'app/shared/model//energy-electricity-data.model';

export interface IEnergyTariffData {
    id?: number;
    description?: string;
    energyElectricityData?: IEnergyElectricityData[];
}

export class EnergyTariffData implements IEnergyTariffData {
    constructor(public id?: number, public description?: string, public energyElectricityData?: IEnergyElectricityData[]) {}
}
