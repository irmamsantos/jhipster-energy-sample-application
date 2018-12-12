import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEnergyCycleData } from 'app/shared/model/energy-cycle-data.model';
import { EnergyCycleDataService } from './energy-cycle-data.service';

@Component({
    selector: 'jhi-energy-cycle-data-delete-dialog',
    templateUrl: './energy-cycle-data-delete-dialog.component.html'
})
export class EnergyCycleDataDeleteDialogComponent {
    energyCycleData: IEnergyCycleData;

    constructor(
        private energyCycleDataService: EnergyCycleDataService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.energyCycleDataService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'energyCycleDataListModification',
                content: 'Deleted an energyCycleData'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-energy-cycle-data-delete-popup',
    template: ''
})
export class EnergyCycleDataDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ energyCycleData }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EnergyCycleDataDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.energyCycleData = energyCycleData;
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
