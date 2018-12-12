import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEnergyCycleData } from 'app/shared/model/energy-cycle-data.model';

@Component({
    selector: 'jhi-energy-cycle-data-detail',
    templateUrl: './energy-cycle-data-detail.component.html'
})
export class EnergyCycleDataDetailComponent implements OnInit {
    energyCycleData: IEnergyCycleData;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ energyCycleData }) => {
            this.energyCycleData = energyCycleData;
        });
    }

    previousState() {
        window.history.back();
    }
}
