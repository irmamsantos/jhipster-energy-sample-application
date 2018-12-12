import { IEnergyVoltageData } from 'app/shared/model//energy-voltage-data.model';
import { IEnergyTariffData } from 'app/shared/model//energy-tariff-data.model';
import { IEnergyCycleData } from 'app/shared/model//energy-cycle-data.model';
import { IEnergyTimePeriodData } from 'app/shared/model//energy-time-period-data.model';

export interface IEnergyElectricityData {
    id?: number;
    energyVoltageData?: IEnergyVoltageData;
    energyTariffData?: IEnergyTariffData;
    energyCycleData?: IEnergyCycleData;
    energyTimePeriodData?: IEnergyTimePeriodData;
}

export class EnergyElectricityData implements IEnergyElectricityData {
    constructor(
        public id?: number,
        public energyVoltageData?: IEnergyVoltageData,
        public energyTariffData?: IEnergyTariffData,
        public energyCycleData?: IEnergyCycleData,
        public energyTimePeriodData?: IEnergyTimePeriodData
    ) {}
}
