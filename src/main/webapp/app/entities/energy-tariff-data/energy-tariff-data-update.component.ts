import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IEnergyTariffData } from 'app/shared/model/energy-tariff-data.model';
import { EnergyTariffDataService } from './energy-tariff-data.service';

@Component({
    selector: 'jhi-energy-tariff-data-update',
    templateUrl: './energy-tariff-data-update.component.html'
})
export class EnergyTariffDataUpdateComponent implements OnInit {
    energyTariffData: IEnergyTariffData;
    isSaving: boolean;

    constructor(private energyTariffDataService: EnergyTariffDataService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ energyTariffData }) => {
            this.energyTariffData = energyTariffData;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.energyTariffData.id !== undefined) {
            this.subscribeToSaveResponse(this.energyTariffDataService.update(this.energyTariffData));
        } else {
            this.subscribeToSaveResponse(this.energyTariffDataService.create(this.energyTariffData));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEnergyTariffData>>) {
        result.subscribe((res: HttpResponse<IEnergyTariffData>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
