import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IEnergyTimePeriodData } from 'app/shared/model/energy-time-period-data.model';
import { EnergyTimePeriodDataService } from './energy-time-period-data.service';

@Component({
    selector: 'jhi-energy-time-period-data-update',
    templateUrl: './energy-time-period-data-update.component.html'
})
export class EnergyTimePeriodDataUpdateComponent implements OnInit {
    energyTimePeriodData: IEnergyTimePeriodData;
    isSaving: boolean;

    constructor(private energyTimePeriodDataService: EnergyTimePeriodDataService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ energyTimePeriodData }) => {
            this.energyTimePeriodData = energyTimePeriodData;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.energyTimePeriodData.id !== undefined) {
            this.subscribeToSaveResponse(this.energyTimePeriodDataService.update(this.energyTimePeriodData));
        } else {
            this.subscribeToSaveResponse(this.energyTimePeriodDataService.create(this.energyTimePeriodData));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEnergyTimePeriodData>>) {
        result.subscribe(
            (res: HttpResponse<IEnergyTimePeriodData>) => this.onSaveSuccess(),
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
}
