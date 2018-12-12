import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IEnergyVoltageData } from 'app/shared/model/energy-voltage-data.model';
import { EnergyVoltageDataService } from './energy-voltage-data.service';

@Component({
    selector: 'jhi-energy-voltage-data-update',
    templateUrl: './energy-voltage-data-update.component.html'
})
export class EnergyVoltageDataUpdateComponent implements OnInit {
    energyVoltageData: IEnergyVoltageData;
    isSaving: boolean;

    constructor(private energyVoltageDataService: EnergyVoltageDataService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ energyVoltageData }) => {
            this.energyVoltageData = energyVoltageData;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.energyVoltageData.id !== undefined) {
            this.subscribeToSaveResponse(this.energyVoltageDataService.update(this.energyVoltageData));
        } else {
            this.subscribeToSaveResponse(this.energyVoltageDataService.create(this.energyVoltageData));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEnergyVoltageData>>) {
        result.subscribe((res: HttpResponse<IEnergyVoltageData>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
