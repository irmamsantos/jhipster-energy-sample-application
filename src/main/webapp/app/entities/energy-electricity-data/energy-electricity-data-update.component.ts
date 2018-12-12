import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IEnergyElectricityData } from 'app/shared/model/energy-electricity-data.model';
import { EnergyElectricityDataService } from './energy-electricity-data.service';
import { IEnergyVoltageData } from 'app/shared/model/energy-voltage-data.model';
import { EnergyVoltageDataService } from 'app/entities/energy-voltage-data';
import { IEnergyTariffData } from 'app/shared/model/energy-tariff-data.model';
import { EnergyTariffDataService } from 'app/entities/energy-tariff-data';
import { IEnergyCycleData } from 'app/shared/model/energy-cycle-data.model';
import { EnergyCycleDataService } from 'app/entities/energy-cycle-data';
import { IEnergyTimePeriodData } from 'app/shared/model/energy-time-period-data.model';
import { EnergyTimePeriodDataService } from 'app/entities/energy-time-period-data';

@Component({
    selector: 'jhi-energy-electricity-data-update',
    templateUrl: './energy-electricity-data-update.component.html'
})
export class EnergyElectricityDataUpdateComponent implements OnInit {
    energyElectricityData: IEnergyElectricityData;
    isSaving: boolean;

    energyvoltagedata: IEnergyVoltageData[];

    energytariffdata: IEnergyTariffData[];

    energycycledata: IEnergyCycleData[];

    energytimeperioddata: IEnergyTimePeriodData[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private energyElectricityDataService: EnergyElectricityDataService,
        private energyVoltageDataService: EnergyVoltageDataService,
        private energyTariffDataService: EnergyTariffDataService,
        private energyCycleDataService: EnergyCycleDataService,
        private energyTimePeriodDataService: EnergyTimePeriodDataService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ energyElectricityData }) => {
            this.energyElectricityData = energyElectricityData;
        });
        this.energyVoltageDataService.query().subscribe(
            (res: HttpResponse<IEnergyVoltageData[]>) => {
                this.energyvoltagedata = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.energyTariffDataService.query().subscribe(
            (res: HttpResponse<IEnergyTariffData[]>) => {
                this.energytariffdata = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.energyCycleDataService.query().subscribe(
            (res: HttpResponse<IEnergyCycleData[]>) => {
                this.energycycledata = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.energyTimePeriodDataService.query().subscribe(
            (res: HttpResponse<IEnergyTimePeriodData[]>) => {
                this.energytimeperioddata = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.energyElectricityData.id !== undefined) {
            this.subscribeToSaveResponse(this.energyElectricityDataService.update(this.energyElectricityData));
        } else {
            this.subscribeToSaveResponse(this.energyElectricityDataService.create(this.energyElectricityData));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEnergyElectricityData>>) {
        result.subscribe(
            (res: HttpResponse<IEnergyElectricityData>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackEnergyVoltageDataById(index: number, item: IEnergyVoltageData) {
        return item.id;
    }

    trackEnergyTariffDataById(index: number, item: IEnergyTariffData) {
        return item.id;
    }

    trackEnergyCycleDataById(index: number, item: IEnergyCycleData) {
        return item.id;
    }

    trackEnergyTimePeriodDataById(index: number, item: IEnergyTimePeriodData) {
        return item.id;
    }
}
