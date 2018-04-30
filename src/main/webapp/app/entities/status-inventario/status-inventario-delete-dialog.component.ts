import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { StatusInventario } from './status-inventario.model';
import { StatusInventarioPopupService } from './status-inventario-popup.service';
import { StatusInventarioService } from './status-inventario.service';

@Component({
    selector: 'jhi-status-inventario-delete-dialog',
    templateUrl: './status-inventario-delete-dialog.component.html'
})
export class StatusInventarioDeleteDialogComponent {

    statusInventario: StatusInventario;

    constructor(
        private statusInventarioService: StatusInventarioService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.statusInventarioService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'statusInventarioListModification',
                content: 'Deleted an statusInventario'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-status-inventario-delete-popup',
    template: ''
})
export class StatusInventarioDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private statusInventarioPopupService: StatusInventarioPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.statusInventarioPopupService
                .open(StatusInventarioDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
