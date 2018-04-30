import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CampoCustomizavelCadastro } from './campo-customizavel-cadastro.model';
import { CampoCustomizavelCadastroPopupService } from './campo-customizavel-cadastro-popup.service';
import { CampoCustomizavelCadastroService } from './campo-customizavel-cadastro.service';

@Component({
    selector: 'jhi-campo-customizavel-cadastro-delete-dialog',
    templateUrl: './campo-customizavel-cadastro-delete-dialog.component.html'
})
export class CampoCustomizavelCadastroDeleteDialogComponent {

    campoCustomizavelCadastro: CampoCustomizavelCadastro;

    constructor(
        private campoCustomizavelCadastroService: CampoCustomizavelCadastroService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.campoCustomizavelCadastroService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'campoCustomizavelCadastroListModification',
                content: 'Deleted an campoCustomizavelCadastro'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-campo-customizavel-cadastro-delete-popup',
    template: ''
})
export class CampoCustomizavelCadastroDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private campoCustomizavelCadastroPopupService: CampoCustomizavelCadastroPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.campoCustomizavelCadastroPopupService
                .open(CampoCustomizavelCadastroDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
