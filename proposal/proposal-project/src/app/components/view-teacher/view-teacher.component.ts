import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DocenteService } from '../../services/docente.service';
import { Docente } from '../../models/docente.model';

@Component({
  selector: 'app-view-teacher',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './view-teacher.component.html',
  styleUrl: './view-teacher.component.css'
})
export class ViewTeacherComponent {
  docentes?: Docente[];
  constructor(private service: DocenteService) {}

  ngOnInit(): void {
    this.consult();
  }

  consult() {
    this.service.get().subscribe(data => {
      this.docentes = data;
      console.log(data);
    });
  }
}
