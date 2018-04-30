import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { LogStatusInventario } from './log-status-inventario.model';
import { LogStatusInventarioPopupService } from './log-status-inventario-popup.service';
import { LogStatusInventarioService } from './log-status-inventario.service';

@Component({
    selector: 'jhi-log-status-inventario-delete-dialog',
    templateUrl: './log-status-inventario-delete-dialog.component.html'
})
export class LogStatusInventarioDeleteDialogComponent {

    logStatusInventario: LogStatusInventario;

    constructor(
        private logStatusInventarioService: LogStatusInventarioService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.logStatusInventarioService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'logStatusInventarioListModification',
                content: 'Deleted an logStatusInventario'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-log-status-inventario-delete-popup',
    template: ''
})
export class LogStatusInventarioDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private logStatusInventarioPopupService: LogStatusInventarioPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.logStatusInventarioPopupService
                .open(LogStatusInventarioDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
