import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEnergyVoltageData } from 'app/shared/model/energy-voltage-data.model';
import { EnergyVoltageDataService } from './energy-voltage-data.service';

@Component({
    selector: 'jhi-energy-voltage-data-delete-dialog',
    templateUrl: './energy-voltage-data-delete-dialog.component.html'
})
export class EnergyVoltageDataDeleteDialogComponent {
    energyVoltageData: IEnergyVoltageData;

    constructor(
        private energyVoltageDataService: EnergyVoltageDataService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.energyVoltageDataService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'energyVoltageDataListModification',
                content: 'Deleted an energyVoltageData'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-energy-voltage-data-delete-popup',
    template: ''
})
export class EnergyVoltageDataDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ energyVoltageData }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EnergyVoltageDataDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.energyVoltageData = energyVoltageData;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
